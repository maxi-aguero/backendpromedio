package com.apress.spring;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final CourseRepository courseRepository;

    public HelloController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // GET /api/cursos → lista todos los cursos
    @GetMapping("/cursos")
    public ResponseEntity<List<CourseResponse>> getAllCourses(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        System.out.println("Usuario autenticado: " + username);

        List<CourseEntity> cursos = courseRepository.findAll();

        List<CourseResponse> responses = cursos.stream()
                .map(curso -> toCourseResponse(curso, HttpStatus.OK.value(), "Curso obtenido correctamente", null))
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        Optional<CourseEntity> cursoOpt = courseRepository.findById(id);
        if (cursoOpt.isEmpty()) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setError("Not Found");
            errorResponse.setMessage("Curso no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        CourseEntity curso = cursoOpt.get();
        CourseResponse response = toCourseResponse(
                curso,
                HttpStatus.OK.value(),
                "Curso obtenido correctamente",
                null
        );
        return ResponseEntity.ok(response);
    }


    // POST /api/cursos → crea un curso nuevo
    @PostMapping("/cursos")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest request,
                                                       @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponse.setError("Bad Request");
            errorResponse.setMessage("El título del curso es obligatorio");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            CourseEntity course = new CourseEntity();
            course.setTitle(request.getTitle());

            CourseEntity saved = courseRepository.save(course);

            CourseResponse successResponse = toCourseResponse(
                    saved,
                    HttpStatus.CREATED.value(),
                    "Course created successfully",
                    null
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

        } catch (Exception e) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setError("Server Error");
            errorResponse.setMessage("Error al crear el curso: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // DELETE /api/cursos/{id}
    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<CourseResponse> deleteCourse(@AuthenticationPrincipal Jwt jwt,
                                                       @PathVariable Long id) {
        String username = jwt.getSubject();

        return courseRepository.findById(id)
                .map(course -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.ok(
                            toCourseResponse(course, HttpStatus.OK.value(), "Course deleted successfully", null)
                    );
                })
                .orElseGet(() -> {
                    CourseResponse errorResponse = new CourseResponse();
                    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    errorResponse.setError("Not Found");
                    errorResponse.setMessage("Curso no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
                });
    }

    // POST /api/cursos/{cursoId}/links
    @PostMapping("/cursos/{cursoId}/links")
    public ResponseEntity<CourseResponse> addLinkToCourse(@PathVariable Long cursoId,
                                                          @RequestBody LinkRequest request,
                                                          @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();

        Optional<CourseEntity> cursoOpt = courseRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setError("Not Found");
            errorResponse.setMessage("Curso no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        CourseEntity curso = cursoOpt.get();

        LinkEntity link = new LinkEntity();
        link.setUrl(request.getUrl());
        link.setDescripcion(request.getDescripcion());
        link.setCurso(curso); // ahora sí es válido

        curso.getLinks().add(link);
        courseRepository.save(curso);

        CourseResponse successResponse = toCourseResponse(
                curso,
                HttpStatus.CREATED.value(),
                "Link agregado correctamente",
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }



    // GET /api/cursos/{cursoId}/links
    @GetMapping("/cursos/{cursoId}/links")
    public ResponseEntity<CourseResponse> getLinksByCourse(@PathVariable Long cursoId,
                                                           @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();

        return courseRepository.findById(cursoId)
                .map(curso -> ResponseEntity.ok(
                        toCourseResponse(curso, HttpStatus.OK.value(), "Links obtenidos correctamente", null)
                ))
                .orElseGet(() -> {
                    CourseResponse errorResponse = new CourseResponse();
                    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
                    errorResponse.setError("Not Found");
                    errorResponse.setMessage("Curso no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
                });
    }

    // DELETE /api/cursos/{cursoId}/links/{linkId}
    @DeleteMapping("/cursos/{cursoId}/links/{linkId}")
    public ResponseEntity<CourseResponse> deleteLinkFromCourse(@PathVariable Long cursoId,
                                                               @PathVariable Long linkId,
                                                               @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();

        Optional<CourseEntity> cursoOpt = courseRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setError("Not Found");
            errorResponse.setMessage("Curso no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        CourseEntity curso = cursoOpt.get();

        Optional<LinkEntity> linkOpt = curso.getLinks().stream()
                .filter(l -> l.getId().equals(linkId))
                .findFirst();

        if (linkOpt.isEmpty()) {
            CourseResponse errorResponse = new CourseResponse();
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            errorResponse.setError("Not Found");
            errorResponse.setMessage("Link no encontrado en este curso");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        curso.getLinks().remove(linkOpt.get());
        courseRepository.save(curso);

        return ResponseEntity.ok(
                toCourseResponse(curso, HttpStatus.OK.value(), "Link eliminado correctamente", null)
        );
    }

    private CourseResponse toCourseResponse(CourseEntity curso, int status, String message, String error) {
        List<LinkResponse> linkResponses = (curso.getLinks() == null ? List.<LinkResponse>of() :
                curso.getLinks().stream()
                        .map(link -> new LinkResponse(link.getId(), link.getUrl(), link.getDescripcion()))
                        .toList()
        );

        CourseResponse response = new CourseResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setError(error);
        response.setId(curso.getId());
        response.setTitle(curso.getTitle());
        response.setLinks(linkResponses);

        return response;
    }

}

package com.quironlabs.api.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.UnexpectedTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quironlabs.api.config.exceptions.BadRequestException;
import com.quironlabs.api.config.exceptions.ForbiddenException;
import com.quironlabs.api.config.exceptions.NotFoundException;
import com.quironlabs.api.config.exceptions.UnauthorizedException;
import com.quironlabs.api.config.responses.BadRequestResponse;
import com.quironlabs.api.config.responses.ForbiddenResponse;
import com.quironlabs.api.config.responses.InternalServerErrorResponse;
import com.quironlabs.api.config.responses.NotFoundResponse;
import com.quironlabs.api.config.responses.UnauthorizedResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomHandlerException {
    private static final Logger logger = LoggerFactory.getLogger(CustomHandlerException.class);

    /**
     * Handle Exceptions to response a HTTP_400_BAD_REQUEST error in custom format.
     * 
     * @param e Exception to Handle.
     * @return <code>ResponseEntity&#60BadRequestResponse&#62</code> with normalized format.
     */
    @ExceptionHandler(
        {BindException.class, BadRequestException.class, HttpMessageNotReadableException.class,
            UnexpectedTypeException.class, HttpRequestMethodNotSupportedException.class}
    )
    public ResponseEntity<BadRequestResponse> handleBadRequestExceptions(Exception e) {

        List<String> details = new ArrayList<>();

        if (e instanceof BindException bindException) {
            BindingResult result = bindException.getBindingResult();

            // Get a list of fields and validations to send details to Client.
            List<FieldError> fieldErrors = result.getFieldErrors();

            details = getErrors(fieldErrors);
        } else if (e instanceof HttpMessageNotReadableException) {

            details.add("El cuerpo de la petición no puede ir vacío.");
        } else {

            details.add(e.getMessage());
        }

        BadRequestResponse response = new BadRequestResponse(details);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Exceptions to response a HTTP_401_UNAUTHORIZED error in custom format.
     * 
     * @param e Exception to Handle.
     * @return <code>ResponseEntity&#60UnauthorizedResponse&#62</code> with normalized format.
     */
    @ExceptionHandler(
        {JwtException.class, ExpiredJwtException.class, UnauthorizedException.class, ServletException.class,
            AuthenticationException.class}
    )
    public ResponseEntity<UnauthorizedResponse> handleUnauthorizedExceptions(Exception e) {
        List<Object> details = new ArrayList<>();
        String message;

        if (e instanceof ExpiredJwtException) {
            message = "JWT expirado.";
        } else if (e instanceof IllegalArgumentException) {
            message = "El token es requerido.";
        } else if (e instanceof UnsupportedJwtException) {
            message = "JWT no soportado.";
        } else if (e instanceof MalformedJwtException) {
            message = "Error en la composición del JWT. ";
        } else if (e instanceof SignatureException) {
            message = "La firma del JWT no coincide y no se debe confiar en ella.";
        } else if (e instanceof UnauthorizedException) {
            message = e.getMessage();
        } else {
            message = "JWT no válido.";
        }

        details.add(message);
        logger.info("-- {} {}", message, e.getMessage());

        UnauthorizedResponse response = new UnauthorizedResponse(details);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle Exceptions to response a HTTP_403_FORBIDDEN error in custom format.
     * 
     * @param e Exception to Handle.
     * @return <code>ResponseEntity&#60ForbiddenResponse&#62</code> with normalized format.
     */
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ForbiddenResponse> handleForbiddenExceptions(Exception e) {
        List<Object> details = new ArrayList<>();

        logger.info("ForbiddenError: {}", e.getMessage());

        details.add(e.getMessage());
        ForbiddenResponse response = new ForbiddenResponse(details);

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle Exceptions to response a HTTP_404_NOT_FOUND error in custom format.
     * 
     * @param e Exception to Handle.
     * @return <code>ResponseEntity&#60NotFoundResponse&#62</code> with normalized format.
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<NotFoundResponse> handleNotFoundExceptions(Exception e) {

        List<String> details = new ArrayList<>();

        details.add(e.getMessage());

        NotFoundResponse response = new NotFoundResponse(details);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Exceptions to response a HTTP_500_INTERNAL_SERVER_ERROR in custom format.
     * 
     * @param e Exception to Handle.
     * @return <code>ResponseEntity&#60InternalServerErrorResponse&#62</code> with normalized format.
     */
    @ExceptionHandler({Exception.class, NullPointerException.class})
    public ResponseEntity<InternalServerErrorResponse> handleAnotherExceptions(Exception e) {

        logger.info("Type of Excption: {}", e.getClass());

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);

        String stackTrace = stringWriter.toString();

        logger.error(stackTrace);

        List<Object> details = new ArrayList<>();

        if (e.getMessage() == null) {
            details.add(e.getMessage());
        }

        InternalServerErrorResponse response = new InternalServerErrorResponse(details);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method used to format and parse a Validations errors. Internal use.
     * 
     * @param fieldErrors | List of all Errors generated from validations
     * @return errors | List of a String with validations errors.
     */
    private List<String> getErrors(List<FieldError> fieldErrors) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : fieldErrors) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return errors;
    }

}

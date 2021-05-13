package br.com.faateodoro.nossacasadocodigo.validacao;

import br.com.faateodoro.nossacasadocodigo.validacao.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ValidacaoErroResponse onValidationException(ValidationException erro){
        return new ValidacaoErroResponse(erro.getCampo(), erro.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    List<ValidacaoErroResponse> onConstraintValidationException(ConstraintViolationException e){
        List<ValidacaoErroResponse> erros = new ArrayList<>() {};
        for (ConstraintViolation erro: e.getConstraintViolations()) {
            erros.add(new ValidacaoErroResponse(
                    erro.getPropertyPath().toString(), erro.getMessage()));
        }
        return erros;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    List<ValidacaoErroResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ValidacaoErroResponse> erros = new ArrayList<>() {};
        for (FieldError erro: e.getBindingResult().getFieldErrors()) {
            erros.add(new ValidacaoErroResponse(
                    erro.getField(), erro.getDefaultMessage()));
        }
        return erros;
    }
}

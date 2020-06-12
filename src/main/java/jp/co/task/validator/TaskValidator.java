package jp.co.task.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.task.validator.annotation.Task;

public class TaskValidator implements ConstraintValidator<Task, String> {


    @Override
    public void initialize(Task task) {
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext con) {

        if (25 < input.length()) {
            return false;
        }
        return true;
    }
}
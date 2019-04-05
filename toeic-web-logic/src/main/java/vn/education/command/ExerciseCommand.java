package vn.education.command;

import vn.education.core.dto.ExerciseDTO;
import vn.education.core.web.command.AbstractCommand;

public class ExerciseCommand extends AbstractCommand<ExerciseDTO> {
    public ExerciseCommand() {
        this.pojo = new ExerciseDTO();
    }
}

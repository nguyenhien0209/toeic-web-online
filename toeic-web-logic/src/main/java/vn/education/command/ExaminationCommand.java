package vn.education.command;

import vn.education.core.dto.ExaminationDTO;
import vn.education.core.web.command.AbstractCommand;

public class ExaminationCommand extends AbstractCommand<ExaminationDTO> {
    public ExaminationCommand() {
        this.pojo = new ExaminationDTO();
    }
}

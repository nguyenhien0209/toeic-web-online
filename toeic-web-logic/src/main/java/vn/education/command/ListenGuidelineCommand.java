package vn.education.command;

import vn.education.core.dto.ListenGuidelineDTO;
import vn.education.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand() {
        this.pojo = new ListenGuidelineDTO();
    }
}

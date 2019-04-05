package vn.education.command;

import vn.education.core.dto.ResultDTO;
import vn.education.core.web.command.AbstractCommand;

public class ResultCommand extends AbstractCommand<ResultDTO> {
     public ResultCommand() {
         this.pojo = new ResultDTO();
     }
}

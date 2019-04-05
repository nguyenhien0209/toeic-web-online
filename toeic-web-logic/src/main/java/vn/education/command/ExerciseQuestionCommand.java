package vn.education.command;

import vn.education.core.dto.ExerciseQuestionDTO;
import vn.education.core.web.command.AbstractCommand;

public class ExerciseQuestionCommand extends AbstractCommand<ExerciseQuestionDTO> {
    private Integer exerciseId;
    private String answerUser;
    private boolean checkAnswer;
    public ExerciseQuestionCommand() {
        this.pojo = new ExerciseQuestionDTO();
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }
}

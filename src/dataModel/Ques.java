package dataModel;

import java.util.Objects;
import java.util.Collection;
public class Ques {

    //Question
    public class Question {
        private final String topic;
        private final Difficulty difficulty;
        private final String questionStatement;
        private final Collection<Option> options;

        public Question(String topic, Difficulty difficulty, String questionStatement, Collection<Option> options) {
            this.topic = topic;
            this.difficulty = difficulty;
            this.questionStatement = questionStatement;
            this.options = options;
        }

        // Getters (if needed)
        public String getTopic() {
            return topic;
        }

        public Difficulty getDifficulty() {
            return difficulty;
        }

        public String getQuestionStatement() {
            return questionStatement;
        }

        public Collection<Option> getOptions() {
            return options;
        }

    }

    //Option
    public class Option {
        private final String text;
        private final boolean isCorrectAnswer;

        public Option(String text, boolean isCorrectAnswer) {
            this.text = text;
            this.isCorrectAnswer = isCorrectAnswer;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Option option = (Option) o;
            return isCorrectAnswer == option.isCorrectAnswer &&
                    Objects.equals(text, option.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text, isCorrectAnswer);
        }
    }
    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }
}

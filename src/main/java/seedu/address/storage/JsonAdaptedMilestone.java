package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.ProjectTask;
import seedu.address.model.project.UniqueProjectTaskList;

/**
 * Jackson-friendly version of {@link seedu.address.model.project.Milestone}.
 */
class JsonAdaptedMilestone {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project milestone's %s field is missing!";

    private final String milestone;
    private final String date;
    private final List<JsonAdaptedProjectTask> projectTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMilestone} with the given milestone details.
     */
    @JsonCreator
    public JsonAdaptedMilestone(@JsonProperty("milestone") String milestone, @JsonProperty("date") String date,
                                @JsonProperty("projectTasks") List<JsonAdaptedProjectTask> projectTasks) {
        this.milestone = milestone;
        this.date = date;
        if (projectTasks != null) {
            this.projectTasks.addAll(projectTasks);
        }
    }

    /**
     * Converts a given {@code Skill} into this class for Jackson use.
     */
    public JsonAdaptedMilestone(Milestone source) {
        milestone = source.milestone;
        date = source.date;
        projectTasks.addAll(source.getProjectTaskList().stream()
                .map(JsonAdaptedProjectTask::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted milestone object into the model's {@code Milestone} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted milestone.
     */
    public Milestone toModelType() throws IllegalValueException {
        final UniqueProjectTaskList modelProjectTasks = new UniqueProjectTaskList();
        for (JsonAdaptedProjectTask task: projectTasks) {
            if (!ProjectTask.isValidTask(task.toModelType())) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        ProjectTask.class.getSimpleName()));
            }
            modelProjectTasks.add(task.toModelType());
        }

        if (!Milestone.isValidMilestone(milestone, date)) {
            throw new IllegalValueException(Milestone.MESSAGE_CONSTRAINTS);
        }
        return new Milestone(milestone, date, modelProjectTasks);
    }

}

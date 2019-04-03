package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.UniqueEmployeeList;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.SortedUserStoryList;
import seedu.address.model.project.UniqueMilestoneList;
import seedu.address.model.project.UserStory;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String client;
    private final String deadline;
    private final String description;
    private final List<JsonAdaptedMilestone> milestones = new ArrayList<>();
    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();
    private final List<JsonAdaptedUserStory> userStories = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName, @JsonProperty("client") String client,
                              @JsonProperty("deadline") String deadline,
                              @JsonProperty("description") String description,
                              @JsonProperty("milestones") List<JsonAdaptedMilestone> milestones,
                              @JsonProperty("employees") List<JsonAdaptedEmployee> employees,
                              @JsonProperty("userStories") List<JsonAdaptedUserStory> userStories) {
        this.projectName = projectName;
        this.client = client;
        this.deadline = deadline;
        this.description = description;
        if (milestones != null) {
            this.milestones.addAll(milestones);
        }
        this.employees.addAll(employees);
        this.userStories.addAll(userStories);
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().projectName;
        client = source.getClient().client;
        deadline = source.getDeadline().deadline;
        description = source.getDescription().description;
        milestones.addAll(source.getMilestones().stream()
            .map(JsonAdaptedMilestone::new)
            .collect(Collectors.toList()));
        employees.addAll(source.getEmployees().stream()
                .map(JsonAdaptedEmployee::new)
                .collect(Collectors.toList()));
        userStories.addAll(source.getUserStories().stream()
                .map(JsonAdaptedUserStory::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final UniqueMilestoneList modelMilestones = new UniqueMilestoneList();
        final UniqueEmployeeList modelEmployees = new UniqueEmployeeList();
        final SortedUserStoryList modelUserStories = new SortedUserStoryList();

        for (JsonAdaptedMilestone milestone : milestones) {
            if (!Milestone.isValidMilestone(milestone.toModelType())) {
                throw new IllegalValueException((String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Milestone.class.getSimpleName())));
            }
            modelMilestones.add(milestone.toModelType());
        }
        for (JsonAdaptedEmployee employee: employees) {
            modelEmployees.add(employee.toModelType());
        }
        for (JsonAdaptedUserStory userStory: userStories) {
            if (!UserStory.isValidUserStory(userStory.toModelType())) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        UserStory.class.getSimpleName()));
            }
            modelUserStories.add(userStory.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelProjectName = new ProjectName(projectName);

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName()));
        }
        if (!Client.isValidName(client)) {
            throw new IllegalValueException(Client.MESSAGE_CONSTRAINTS);
        }
        final Client modelClient = new Client(client);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDate(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        return new Project(modelProjectName, modelClient, modelDeadline, modelMilestones, modelDescription,
                modelEmployees, modelUserStories);
    }

}

package com.example.processing;

import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import com.example.dto.CollaboratorDTO;
import com.example.model.CollaborationDuration;
import com.example.model.EmployeePair;
import com.example.model.EmployeeProject;
import com.example.util.DateIntersector;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

@Component
public class CSVProcessorImpl implements CSVProcessor {
    public CollaboratorDTO Process(InputStream FileInformation) {
        TreeMap<Integer, TreeSet<EmployeeProject>> ProjectTeams = GetProjectTeams(FileInformation);
        return FindLongestDuo(ProjectTeams);
    }

    private TreeMap<Integer, TreeSet<EmployeeProject>> GetProjectTeams(InputStream FileInformation) {
        TreeMap<Integer, TreeSet<EmployeeProject>> ProjectTeams = new TreeMap<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(FileInformation))) {
            String[] RowFields;
            while ((RowFields = reader.readNext()) != null) {
                String EmpIDRaw = RowFields[0].trim();
                String ProjectIDRaw = RowFields[1].trim();
                String DateFrom = RowFields[2].trim();
                String DateTo = RowFields[3].trim();

                // Skip header row if it is present+6
                if (EmpIDRaw.equals("EmpID") &&
                        ProjectIDRaw.equals("ProjectID") &&
                        DateFrom.equals("DateFrom") &&
                        DateTo.equals("DateTo")) {
                    continue;
                }

                Integer EmpID;
                Integer ProjectID;

                try {
                    EmpID = Integer.parseInt(EmpIDRaw);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Employee ID " + EmpIDRaw + " is incorrect");
                }

                try {
                    ProjectID = Integer.parseInt(ProjectIDRaw);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Project ID " + ProjectIDRaw + " is incorrect");
                }

                DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate LocalDateFrom;
                LocalDate LocalDateTo = LocalDate.now();

                try {
                    LocalDateFrom = LocalDate.parse(DateFrom, Formatter);
                    if (!DateTo.equals("NULL")) {
                        LocalDateTo = LocalDate.parse(DateTo, Formatter);
                    }

                    EmployeeProject entity = new EmployeeProject(EmpID, ProjectID, LocalDateFrom, LocalDateTo);
                    TreeSet<EmployeeProject> team = ProjectTeams.getOrDefault(ProjectID,
                            new TreeSet<EmployeeProject>());
                    team.add(entity);
                    ProjectTeams.putIfAbsent(ProjectID, team);

                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Could not parse date: " + e.getMessage());
                }
            }
        } catch (CsvValidationException | IOException e) {
            System.out.println(e.getMessage());
        }
        return ProjectTeams;
    }

    private CollaboratorDTO FindLongestDuo(TreeMap<Integer, TreeSet<EmployeeProject>> ProjectTeams) {
        // Define default values
        long LongestCollaboration = Long.MIN_VALUE;
        EmployeePair LongestCollaborators = new EmployeePair(0, 0);

        Map<EmployeePair, TreeSet<CollaborationDuration>> CollaborationLeaderboard = new HashMap<>();

        // Iterate through all projects
        for (Map.Entry<Integer, TreeSet<EmployeeProject>> entry : ProjectTeams.entrySet()) {
            TreeSet<EmployeeProject> TeamMember = entry.getValue();
            Integer IteratedProject = entry.getKey();

            // Iterate through all employees of each project
            for (EmployeeProject CurrentEmployeeProject : TeamMember) {
                EmployeeProject NextEmployeeProject = TeamMember.higher(CurrentEmployeeProject);

                // Find team membership intersection periods
                while (NextEmployeeProject != null) {
                    long PeriodWorkedTogether = DateIntersector.Intersect(CurrentEmployeeProject, NextEmployeeProject);

                    // Omit non-intersecting entries
                    if (PeriodWorkedTogether == 0) {
                        NextEmployeeProject = TeamMember.higher(NextEmployeeProject);
                        continue;
                    }

                    EmployeePair EmployeePair = new EmployeePair(CurrentEmployeeProject.getEmployeeID(),
                            NextEmployeeProject.getEmployeeID());
                    CollaborationDuration CollaborationDuration = new CollaborationDuration(IteratedProject,
                            PeriodWorkedTogether);

                    CollaborationLeaderboard.putIfAbsent(EmployeePair, EmptyCollaborations());
                    TreeSet<CollaborationDuration> Collaborations = CollaborationLeaderboard.get(EmployeePair);
                    Collaborations.add(CollaborationDuration);

                    // TODO: Think whether you have time and energy to display equal lengths of
                    // different pairs
                    if (LongestCollaboration < PeriodWorkedTogether) {
                        LongestCollaboration = PeriodWorkedTogether;
                        LongestCollaborators = new EmployeePair(
                                CurrentEmployeeProject.getEmployeeID(),
                                NextEmployeeProject.getEmployeeID());
                    }

                    NextEmployeeProject = TeamMember.higher(NextEmployeeProject);
                }
            }
        }

        CollaboratorDTO dao = new CollaboratorDTO(LongestCollaborators,
                CollaborationLeaderboard.get(LongestCollaborators));
        return dao;
    }

    private TreeSet<CollaborationDuration> EmptyCollaborations() {
        return new TreeSet<CollaborationDuration>();
    }
}

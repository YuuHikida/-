package analix.DHIT.service;

import analix.DHIT.mapper.TeamMapper;
import analix.DHIT.model.Assignment;
import analix.DHIT.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {

    private final TeamMapper teamMapper;
    private final AssignmentRepository assignmentRepository;
    public AssignmentService(TeamMapper teamMapper, AssignmentRepository assignmentRepository) {
        this.teamMapper = teamMapper;
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAssignmentByEmployeeCode(int employeeCode)
    {
        List<Assignment> assignments = this.assignmentRepository.selectByUser(employeeCode);
        if (assignments == null) {
            return new ArrayList<>();
        }
        return assignments;
    }

    public List<Assignment> getAssignmentByTeam(int teamId)
    {
        List<Assignment> assignments = this.assignmentRepository.selectByTeam(teamId);
        if (assignments == null) {
            return new ArrayList<>();
        }
        return assignments;
    }

    public void deleteById(int assignmentId) {
        this.assignmentRepository.deleteById(assignmentId);
    }

    public void create(Assignment assignment){
        this.assignmentRepository.save(assignment);
    }

    public boolean existsAssignment(int employeeCode, int teamId) {
        int count = teamMapper.countAssignmentByEmployeeCodeAndTeamId(employeeCode, teamId);
        return count > 0;
    }




}

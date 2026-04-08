package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories.IssueRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public long countIssuesByAssigneeUserIdDerived(Long userId) {
        return issueRepository.countAllByAssignee_UserId(userId);
    }

    @Override
    public long countIssuesByAssigneeUserIdSql(Long userId) {
        return issueRepository.countAllByAssigneeIdSql(userId);
    }

    @Override
    public long countIssuesByAssigneeUserIdJpql(Long userId) {
        return issueRepository.countAllByAssigneeIdJpql(userId);
    }
}

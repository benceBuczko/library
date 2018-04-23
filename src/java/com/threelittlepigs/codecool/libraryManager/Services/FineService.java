package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Entities.Fine;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import com.threelittlepigs.codecool.libraryManager.Repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FineService {

    @Autowired
    FineRepository fineRepository;

    public Fine getFineById(int id) {
        return fineRepository.findOne((long) id);
    }

    public List<Fine> findAll(){
        return fineRepository.findAll();
    }

    public void changeAmount(int id, double amount) {
        Fine fine = getFineById(id);
        fine.setAmount(amount);
    }

    public void saveFine(Fine fine) {
        fineRepository.save(fine);
    }

    public void changeOccurrence(int id, Date occurrence) {
        Fine fine = getFineById(id);
        fine.setOccurrence(occurrence);
    }

    public void changeDueDate(int id, Date dueDate) {
        Fine fine = getFineById(id);
        fine.setDueDate(dueDate);
    }

    public void changeStatus(int id, boolean status) {
        Fine fine = getFineById(id);
        fine.setStatus(status);
    }

    public void changeMember(int fineId, int memberId) {
        Fine fine = getFineById(fineId);
        /*List<Member> members = EntityUtility.findByOneCriteria(User.class, "id", String.valueOf(memberId));
        fine.setMember(members.get(0));*/
    }

    public List<Fine> getFinesByMemberId(long member, boolean status) {
        return fineRepository.getFinesByMemberIdAndStatus(member,status);
    }
}

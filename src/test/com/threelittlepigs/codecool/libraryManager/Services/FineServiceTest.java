package com.threelittlepigs.codecool.libraryManager.Services;

import com.threelittlepigs.codecool.libraryManager.Entities.Fine;
import com.threelittlepigs.codecool.libraryManager.Entities.Users.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FineServiceTest {

    private FineService fineService;
    private Fine fine;
    private Member member;

    @BeforeAll
    void init() {
        fineService = new FineService();
        fine = fineService.getFineById(1);
        member = mock(Member.class);
        when(member.getId()).thenReturn(2L);
    }

    @Test
    void changeAmountTest() {

        Double formerAmount = fine.getAmount();
        fineService.changeAmount((int) fine.getId(), formerAmount + 5);
        Double presentAmount = fine.getAmount();
        assertNotEquals(formerAmount, presentAmount);
    }

    @Test
    void changeOccuranceTest() {
        Date formerDate = fine.getOccurrence();
        Date presentDate = new Date();
        fineService.changeOccurrence((int) fine.getId(), presentDate);
        assertNotEquals(formerDate, presentDate);

    }

    @Test
    void changeDueDateTest() {
        Date formerDate = fine.getDueDate();
        Date presentDate = new Date();
        fineService.changeDueDate((int) fine.getId(), presentDate);
        assertNotEquals(formerDate, presentDate);
    }

    @Test
    void changeStatusTest() {
        Boolean formerStatus = fine.isStatus();
        fineService.changeStatus((int) fine.getId(), !formerStatus);
        assertNotEquals(formerStatus, fine.isStatus());
    }

    @Test
    void changeMemberTest() {
        fineService.changeMember((int) fine.getId(), (int)member.getId());
        assertEquals(member.getId(), fine.getMember().getId());
    }

    @Test
    void getFinesByUserIdTest() {
        /*List<Fine> userFines = EntityUtility.getEntityManager().createNamedQuery("getFineByMember", Fine.class).setParameter("memberid", member.getId()).getResultList();
        assertEquals(1, userFines.size());*/
    }

    @Test
    void getFineByIdTest() {
        assertEquals(1, fine.getId());
    }

}
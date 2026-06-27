package com.example.tenant_service.service;

import com.example.tenant_service.dto.*;
import com.example.tenant_service.entity.*;
import com.example.tenant_service.enums.ComplaintStatus;
import com.example.tenant_service.enums.RentStatus;
import com.example.tenant_service.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyDataService {

    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;
    private final RentRepository rentRepository;
    private final ComplaintRepository complaintRepository;
    private final MealRepository mealRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecentActivityRepository recentActivityRepository;

    @PostConstruct
    public void insertDummyData() {

        if (userRepository.count() > 0) {
            return;
        }

        Hostel hostel = new Hostel();
        hostel.setAccommodationId("1");
        hostel.setName("Kalpana Chawla Hostel,Sector 21,Gurgaon");
        hostel.setAddress("Ghaziabad");
        hostelRepository.save(hostel);

        Room room = new Room();
        room.setRoomNumber("A-101");
        room.setHostel(hostel);
        room.setRoomCapacity(1);
        room.setRoomCapacityFilled(1);
        room.setRemark("");
        room.setRentAmount(10000.0);
        roomRepository.save(room);

        Room room1 = Room.builder()
                .roomNumber("101")
                .roomCapacity(3)
                .roomCapacityFilled(2)
                .remark("")
                .rentAmount(8000.0)
                .hostel(hostel)
                .build();

        Room room2 = Room.builder()
                .roomNumber("102")
                .roomCapacity(2)
                .roomCapacityFilled(0)
                .remark("")
                .rentAmount(8000.0)
                .hostel(hostel)
                .build();

        roomRepository.saveAll(List.of(room1, room2));

        User user1 = new User();
        user1.setUserName("9812657662");
        user1.setPassword(passwordEncoder.encode("123456"));
        user1.setName("Komal");
        user1.setRole("TENANT");
        user1.setStatus("ACTIVE");
        user1.setAadhaarVerified(true);
        user1.setAdvanceKycVerified(true);
        user1.setDocVerified(false);
        user1.setCareOf("Rann Singh");
        user1.setDob("10/12/2003");
        user1.setEmailId("ky460998@gmail.com");
        user1.setEmploymentNature("FULL_TIME");
        user1.setGender("F");
        user1.setLocalRef("Nitin");
        user1.setLocalRef1("");
        user1.setLocalRef2("");
        user1.setLocalRefMobile("9876453431");
        user1.setLocalRefRelation("brother");
        user1.setOccupation("employee");
        user1.setOfficeAddress("Gurugram,sector 21");
        user1.setOfficeNumber("1234567890");
        user1.setPermanentAddress("Jhajjar");
        user1.setLocalAddress("Jhajjar");
        user1.setPinCode("124109");
        user1.setRentalRecord("clear");
        user1.setUuid("qwef-213rf-1324");
        user1.setDueDate("01/04/2026");
        user1.setInitialMeterReading("2300");
        user1.setJoiningDate(LocalDate.of(2026, 4, 6));
        user1.setNoticeEndDate("01/05/2026");
        user1.setNoticeStartDate("01/04/2026");
        user1.setNoticeStatus("APPROVED");
        user1.setPerDayLateCharge("0.0");
        user1.setRentAmount("10000");
        user1.setSecurityDeposit("3000");
        user1.setOnNotice(true);
        user1.setRoom(room);
        user1.setHostel(hostel);

        User user2= new User();
        user2.setUserName("8930841852");
        user2.setPassword(passwordEncoder.encode("987654"));
        user2.setName("Himanshi");
        user2.setRole("TENANT");
        user2.setStatus("ACTIVE");
        user2.setAadhaarVerified(true);
        user2.setAdvanceKycVerified(true);
        user2.setDocVerified(false);
        user2.setCareOf("Naresh");
        user2.setDob("21/09/2003");
        user2.setEmailId("hima1221@gmail.com");
        user2.setEmploymentNature("FULL_TIME");
        user2.setGender("F");
        user2.setLocalRef("ravi");
        user2.setLocalRef1("");
        user2.setLocalRef2("");
        user2.setLocalRefMobile("1234567890");
        user2.setLocalRefRelation("brother");
        user2.setOccupation("employee");
        user2.setOfficeAddress("Gurugram,sector 17");
        user2.setOfficeNumber("8765423456");
        user2.setPermanentAddress("Rohtak");
        user2.setLocalAddress("Rohtak");
        user2.setPinCode("124408");
        user2.setRentalRecord("clear");
        user2.setUuid("asdf-1234-xcvb");
        user2.setDueDate("01/04/2026");
        user2.setInitialMeterReading("1000");
        user2.setJoiningDate(LocalDate.of(2026, 4, 6));
        user2.setNoticeEndDate("01/05/2026");
        user2.setNoticeStartDate("01/04/2026");
        user2.setNoticeStatus("APPROVED");
        user2.setPerDayLateCharge("0.0");
        user2.setRentAmount("8000");
        user2.setSecurityDeposit("2000");
        user2.setOnNotice(true);
        user2.setRoom(room1);
        user2.setHostel(hostel);

        User user3= new User();
        user3.setUserName("9991711218");
        user3.setPassword(passwordEncoder.encode("101010"));
        user3.setName("Parveen");
        user3.setRole("TENANT");
        user3.setStatus("ACTIVE");
        user3.setAadhaarVerified(true);
        user3.setAdvanceKycVerified(true);
        user3.setDocVerified(false);
        user3.setCareOf("Puroshuttam");
        user3.setDob("12/10/2003");
        user3.setEmailId("parveen1012@gmail.com");
        user3.setEmploymentNature("FULL_TIME");
        user3.setGender("M");
        user3.setLocalRef("Bharti");
        user3.setLocalRef1("");
        user3.setLocalRef2("");
        user3.setLocalRefMobile("4567321467");
        user3.setLocalRefRelation("sister");
        user3.setOccupation("Student");
        user3.setOfficeAddress("Gurugram,sector21");
        user3.setOfficeNumber("3668747332");
        user3.setPermanentAddress("Bhiwani");
        user3.setLocalAddress("Bhiwani");
        user3.setPinCode("127021");
        user3.setRentalRecord("clear");
        user3.setUuid("hjkl-6789-uhvf");
        user3.setDueDate("01/04/2026");
        user3.setInitialMeterReading("1000");
        user3.setJoiningDate(LocalDate.of(2026, 4, 6));
        user3.setNoticeEndDate("01/05/2026");
        user3.setNoticeStartDate("01/04/2026");
        user3.setNoticeStatus("APPROVED");
        user3.setPerDayLateCharge("0.0");
        user3.setRentAmount("8000");
        user3.setSecurityDeposit("2000");
        user3.setOnNotice(false);
        user3.setRoom(room1);
        user3.setHostel(hostel);

        User user4 = new User();
        user4.setUserName("9812657662");
        user4.setPassword(passwordEncoder.encode("123456"));
        user4.setName("Komal");
        user4.setRole("MANAGER");
        user4.setRoom(room);
        user4.setHostel(hostel);

        User user5 = new User();
        user5.setUserName("9812657662");
        user5.setPassword(passwordEncoder.encode("123456"));
        user5.setName("Komal");
        user5.setRole("OWNER");
        user5.setRoom(room);
        user5.setHostel(hostel);
        userRepository.saveAll(List.of(user1, user2, user3,user4,user5));

        Rent rent = new Rent();
        rent.setAmount(5000.0);
        rent.setPaidAmount(2000.0);
        rent.setStatus(RentStatus.UNPAID);
        rent.setDueDate(LocalDate.now().plusDays(5));
        rent.setUser(user1);
        rentRepository.save(rent);

        Rent rent2 = new Rent();
        rent2.setInvoiceId("INV-" + (System.currentTimeMillis()+1));
        rent2.setUser(user1);
        rent2.setAmount(5000.0);
        rent2.setPaidAmount(0.0);
        rent2.setStatus(RentStatus.OVERDUE);
        rent2.setDueDate(LocalDate.now().minusDays(3));
        rentRepository.save(rent2);

        Complaint complaint1 = new Complaint();
        complaint1.setComplaintNumber("CLT-1001");
        complaint1.setSubject("Fan not working");
        complaint1.setAccommodationId("ACC101");
        complaint1.setStatus(ComplaintStatus.RAISED);
        complaint1.setCreatedAt(LocalDateTime.now());
        complaint1.setUser(user1);
        complaint1.setDescription("Fan not rotating");
        complaint1.setRoomId("101");
        complaint1.setHasAttachment(false);

        Complaint complaint2 = new Complaint();
        complaint2.setComplaintNumber("CLT-1002");
        complaint2.setSubject("AC not cooling");
        complaint2.setDescription("Ac not working");
        complaint2.setAccommodationId("ACC101");
        complaint2.setRoomId("101");
        complaint2.setStatus(ComplaintStatus.RAISED);
        complaint2.setHasAttachment(false);
        complaint2.setCreatedAt(LocalDateTime.now());
        complaint2.setUser(user1);

        complaintRepository.save(complaint1);
        complaintRepository.save(complaint2);

        for (int i = 1; i <= 7; i++) {

            Meal meal = new Meal();
            meal.setHostel(hostel);
            meal.setDay(i);

            switch (i) {

                case 1:
                    meal.setBreakfastItems(List.of("Poha", "Tea"));
                    meal.setLunchItems(List.of("Dal", "Rice"));
                    meal.setDinnerItems(List.of("Roti", "Sabzi"));
                    break;

                case 2:
                    meal.setBreakfastItems(List.of("Aloo Paratha", "Curd"));
                    meal.setLunchItems(List.of("Rajma", "Rice"));
                    meal.setDinnerItems(List.of("Chapati", "Paneer"));
                    break;

                case 3:
                    meal.setBreakfastItems(List.of("Upma", "Coffee"));
                    meal.setLunchItems(List.of("Chole", "Rice"));
                    meal.setDinnerItems(List.of("Roti", "Mix Veg"));
                    break;

                case 4:
                    meal.setBreakfastItems(List.of("Bread", "Butter"));
                    meal.setLunchItems(List.of("Kadhi", "Rice"));
                    meal.setDinnerItems(List.of("Roti", "Bhindi"));
                    break;

                case 5:
                    meal.setBreakfastItems(List.of("Sandwich", "Tea"));
                    meal.setLunchItems(List.of("Pulao", "Raita"));
                    meal.setDinnerItems(List.of("Chapati", "Aloo Gobi"));
                    break;

                case 6:
                    meal.setBreakfastItems(List.of("Idli", "Sambar"));
                    meal.setLunchItems(List.of("Dal Fry", "Rice"));
                    meal.setDinnerItems(List.of("Roti", "Paneer Butter Masala"));
                    break;

                case 7:
                    meal.setBreakfastItems(List.of("Chole Bhature"));
                    meal.setLunchItems(List.of("Veg Biryani", "Raita"));
                    meal.setDinnerItems(List.of("Chapati", "Mix Veg"));
                    break;
            }

            meal.setBreakfastTiming("08:00 AM - 09:30 AM");
            meal.setLunchTiming("01:00 PM - 02:00 PM");
            meal.setDinnerTiming("08:00 PM - 09:00 PM");

            mealRepository.save(meal);

        }
    }

    public OccupancyStatusDto getOccupancyStatus() {
        OccupancyStatusDto dto = new OccupancyStatusDto();

        dto.setAccommodationName("Kalpana Chawla Hostel, Sector 21, Gurgaon");
        dto.setTotalBeds(5);
        dto.setOccupiedBeds(2);
        dto.setNewlyFilled(1);
        dto.setVacated(0);
        dto.setOnNotice(1);

        return dto;
    }

    public CollectionStatusDto getCollectionStatus() {
        CollectionStatusDto dto = new CollectionStatusDto();

        dto.setAccommodationName("Kalpana Chawla Hostel, Sector 21, Gurgaon");
        dto.setCollected(15000);
        dto.setTotal(48199);
        dto.setOverdueAmount(18066);
        dto.setApprovalPending(2133);

        return dto;
    }

    /*public List<ActionItemDto> getActionItems() {
        return List.of(
                new ActionItemDto("PENDING_ONBOARDING", "New Tenant Request", "1 new request", "Today"
                ),
                new ActionItemDto("PAYMENT_APPROVAL", "Overdue Invoices", "3 invoices overdue", "Today"
                )
        );
    }*/

    public List<ActionItemDto> getActionItems(String accommodationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = userRepository.findByUserName(username);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);
         accommodationId = user.getHostel().getAccommodationId();

        List<ActionItemDto> actions = new ArrayList<>();

        long pendingOnboarding = userRepository
                .countByHostel_AccommodationIdAndRoomIsNull(accommodationId);

        if (pendingOnboarding > 0) {
            actions.add(new ActionItemDto(
                    "PENDING_ONBOARDING",
                    "New Tenant Request",
                    pendingOnboarding + " pending",
                    "Today"
            ));
        }

        long overdueCount = rentRepository
                .countByUser_Hostel_AccommodationIdAndStatus(accommodationId,RentStatus.OVERDUE );

        if (overdueCount > 0) {
            actions.add(new ActionItemDto(
                    "PAYMENT_OVERDUE",
                    "Overdue Invoices",
                    overdueCount + " invoices overdue",
                    "Today"
            ));
        }

        return actions;
    }

    /*public List<RecentActivitiesDto> getRecentActivities() {
        return List.of(
                new RecentActivitiesDto("Payment initiated of 101 by Komal Yadav", "PAYMENT_RECEIVED", "40 min ago"
                ),
                new RecentActivitiesDto("Complaint regarding Fan in Room 101 resolved", "COMPLAINT_RESOLVED", "1d ago"
                ),
                new RecentActivitiesDto("New Fan complaint raised from Room 101", "COMPLAINT_RAISED", "7d ago"
                )
        );
    }*/
    public List<RecentActivitiesDto> getRecentActivities() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        List<User> users = userRepository.findByUserName(username);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);
        Long accommodationId = Long.valueOf(user.getHostel().getAccommodationId());

        return recentActivityRepository
                .findTop10ByAccommodationIdOrderByCreatedAtDesc(accommodationId)
                 .stream()
                .map(activity -> new RecentActivitiesDto(
                        activity.getMessage(),
                        activity.getType(),
                        getTimeAgo(activity.getCreatedAt())
                ))
                .toList();
    }
    private String getTimeAgo(LocalDateTime time) {

        long minutes = ChronoUnit.MINUTES.between(time, LocalDateTime.now());

        if (minutes < 60) return minutes + " min ago";

        long hours = minutes / 60;
        if (hours < 24) return hours + " hrs ago";

        long days = hours / 24;
        return days + " d ago";
    }
}





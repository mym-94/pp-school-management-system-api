package com.sms.repository;

import com.sms.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>AcademicYear Repository</h1>
 *
 * @author Mahmoud Yahia
 * @since January 2020
 * @version 1.0.0
 */

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
}

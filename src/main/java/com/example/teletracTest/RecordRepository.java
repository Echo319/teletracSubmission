package com.example.teletracTest;

import com.example.teletracTest.Data.Record;
import org.springframework.data.jpa.repository.JpaRepository;

interface RecordRepository extends JpaRepository<Record, Long> {
}

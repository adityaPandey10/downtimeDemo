package com.downtime.demo.repository;

import com.downtime.demo.model.Downtime;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DowntimeRepo extends JpaRepository<Downtime, Long> {

    Downtime findByPName(String name);

}

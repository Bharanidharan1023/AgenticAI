package com.agenticai.agentic_ai_demo_1.repository;

import com.agenticai.agentic_ai_demo_1.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends  JpaRepository<CarModel, Integer>{
    @NativeQuery("SELECT * FROM doctor ORDER BY id DESC LIMIT :limit")
    public List<CarModel> findCarsByLimitDesc(int limit);
}

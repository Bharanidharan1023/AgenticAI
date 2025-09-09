package com.agenticai.agentic_ai_demo_1.service;

import com.agenticai.agentic_ai_demo_1.model.CarModel;
import com.agenticai.agentic_ai_demo_1.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CarService")
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Tool(description = "fetch last N cars from the database")
    public List<CarModel> getLastNDoctors(int limit) {
        return carRepository.findCarsByLimitDesc(limit);
    }

    @Tool(description = "add new cars to the database by providing car name and type of car(e.g. sports, suv" +
            " which will return the unique id of the added car")
    public int add(String brand, String model, String year) {
        CarModel car = new CarModel();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        CarModel save = carRepository.save(car);
        return save.getId();
    }

}

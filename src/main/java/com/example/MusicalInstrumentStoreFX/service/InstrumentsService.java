package com.example.MusicalInstrumentStoreFX.service;

import com.example.MusicalInstrumentStoreFX.model.entity.Instruments;
import com.example.MusicalInstrumentStoreFX.model.repository.InstrumentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstrumentsService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentsService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    // Метод для создания инструмента
    public void create(Instruments instruments) {
        instrumentRepository.save(instruments);
    }

    // Метод для получения списка всех инструментов, исключая те, у которых count == 0
    public ObservableList<Instruments> getListInstruments() {
        // Загружаем все инструменты из базы данных
        List<Instruments> instruments = instrumentRepository.findAll();

        // Фильтруем только те инструменты, у которых количество больше 0
        List<Instruments> availableInstruments = instruments.stream()
                .filter(instrument -> instrument.getCount() > 0)
                .collect(Collectors.toList());

        // Конвертируем отфильтрованный список в ObservableList
        ObservableList<Instruments> listInstruments = FXCollections.observableArrayList();
        listInstruments.addAll(availableInstruments);

        return listInstruments;
    }

    // Метод для уменьшения доступного количества инструмента
    public boolean decreaseInstrumentCount(Long instrumentId) {
        Optional<Instruments> optionalInstrument = instrumentRepository.findById(instrumentId);

        if (optionalInstrument.isPresent()) {
            Instruments instrument = optionalInstrument.get();

            // Проверяем, что количество больше нуля, прежде чем уменьшить
            if (instrument.getCount() > 0) {
                instrument.setCount(instrument.getCount() - 1);  // Уменьшаем количество
                instrumentRepository.save(instrument);  // Сохраняем обновленный инструмент
                return true;  // Успешно уменьшили количество
            }
        }

        return false;  // Если инструмент не найден или количество == 0
    }

    // Метод для увеличения количества инструмента
    public boolean increaseInstrumentCount(Long instrumentId, int quantity) {
        Optional<Instruments> optionalInstrument = instrumentRepository.findById(instrumentId);

        if (optionalInstrument.isPresent()) {
            Instruments instrument = optionalInstrument.get();

            // Увеличиваем количество инструмента
            instrument.setCount(instrument.getCount() + quantity);
            instrumentRepository.save(instrument);  // Сохраняем обновленный инструмент
            return true;  // Успешно увеличили количество
        }

        return false;  // Если инструмент не найден
    }

    public void deleteInstrumentAndResortIds(Long instrumentId) {
        // Находим инструмент по ID
        Optional<Instruments> instrumentToDelete = instrumentRepository.findById(instrumentId);

        if (instrumentToDelete.isPresent()) {
            // Удаляем инструмент
            instrumentRepository.delete(instrumentToDelete.get());

            // Перенумеровываем ID оставшихся инструментов
            List<Instruments> instrumentsList = instrumentRepository.findAll();
            for (int i = 0; i < instrumentsList.size(); i++) {
                instrumentsList.get(i).setId((long) (i + 1));
                instrumentRepository.save(instrumentsList.get(i));
            }
        } else {
            System.out.println("Инструмент с ID " + instrumentId + " не найден.");
        }
    }

    // Метод для обновления инструмента
    public void update(Instruments instrument) {
        instrumentRepository.save(instrument);  // Метод save() из JpaRepository автоматически обновит объект, если его ID существует
    }
}

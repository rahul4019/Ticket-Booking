package ticket.booking.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private Train train;

    // trainList a list of type Train
    private List<Train> trainList;

    private ObjectMapper objectMapper = new ObjectMapper();

    // final is used so when it gets initialized no one can change it
    private final String TRAIN_PATH = "../localDb/trains.json";

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}

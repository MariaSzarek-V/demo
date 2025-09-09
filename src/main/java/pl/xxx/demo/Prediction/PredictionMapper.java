//package pl.xxx.demo.Prediction;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
//
//public class PredictionMapper {
//
//    //entity -> DTO
//    public static PredictionDTO toDto(Prediction entity) {
//        if (entity == null) {
//            return null;
//        }
//        PredictionDTO dto = new PredictionDTO();
//        dto.setPredictedHomeScore(entity.getPredictedHomeScore());
//        dto.setPredictedAwayScore(entity.getPredictedAwayScore());
//        return dto;
//    }
//
//    //DTO -> entity
//    public static Prediction toEntity(PredictionDTO dto) {
//        if (dto == null) {
//            return null;
//        }
//        Prediction entity = new Prediction();
//        entity.setPredictedHomeScore(dto.getPredictedHomeScore());
//        entity.setPredictedAwayScore(dto.getPredictedAwayScore());
//        return entity;
//    }
//
//    //LISTA entity -> DTO (do get listy)
//    public static List<PredictionDTO> toDtoList(List<Prediction> entities) {
//        if (entities == null) {
//            return new ArrayList<>();
//        }
//        return entities.stream()
//                .map(PredictionMapper::toDto)
//                .collect(Collectors.toList());
//    }
//}
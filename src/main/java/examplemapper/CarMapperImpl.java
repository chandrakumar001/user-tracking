package examplemapper;

public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto toDto(Car car) {
        if ( car == null ) {
            return null;
        }
        CarDto carDto = new CarDto();
        carDto.setEngineDto( toDto( car.getEngine() ) );
        carDto.setId( car.getId() );
        carDto.setMake( car.getMake() );
        carDto.setNumOfSeats( car.getNumOfSeats() );
        carDto.setReleaseDate( car.getReleaseDate() );
        return carDto;
    }

    @Override
    public EngineDto toDto(Engine engine) {
        if ( engine == null ) {
            return null;
        }
        EngineDto engineDto = new EngineDto();
        engineDto.setType( engine.getType() );
        return engineDto;
    }
}
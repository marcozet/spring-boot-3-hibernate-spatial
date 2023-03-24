package springboot3;

import java.util.Optional;
import java.util.UUID;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GeometryEntityRepository extends CrudRepository<GeometryEntity, UUID> {

  @Query(value = "select extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtent();

  @Query(value = "select st_extent(the_geom) from field", nativeQuery = true)
  Optional<Polygon> getExtentByNativeQuery();

}

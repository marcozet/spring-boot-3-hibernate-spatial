package springboot3;

import java.util.Optional;
import java.util.UUID;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GeometryEntityRepository extends JpaRepository<GeometryEntity, UUID> {

  @Query(value = "select extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtentByHQLQuery();

  @Query(value = "select st_extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtentByST_PrefixedFunctionName();

  @Query(value = "select st_extent(the_geom) from field", nativeQuery = true)
  Optional<Polygon> getExtentByNativeQuery();
}

package springboot3;

import java.util.Optional;
import java.util.UUID;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GeometryEntityRepository extends JpaRepository<GeometryEntity, UUID> {

  @Query(value = "select extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtentByJPQL();

  @Query(value = "select st_extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtentByST_PrefixedFunctionName();

  @Query(value = "select st_extent(the_geom) from geometry_entity", nativeQuery = true)
  Optional<Polygon> getExtentByNativeQuery();

  @Query(value = "select envelope(geometry) from GeometryEntity")
  Optional<Polygon> getEnvelopeByJPQL();

  @Query(value = "select st_envelope(geometry) from GeometryEntity")
  Optional<Polygon> getEnvelopeByST_PrefixedFunctionName();

  @Query(value = "select st_envelope(the_geom) from geometry_entity", nativeQuery = true)
  Optional<Polygon> getEnvelopeByNativeQuery();
}

package springboot3;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;

class SpringBoot3ApplicationTests extends AbstractIT {

  @Autowired
  private GeometryEntityRepository repo;

  @Test
  void testGetExtentWithHql() {
    repo.getExtent();
  }

  @Test
  void testExtentWithNativeQuery() {
    Optional<Polygon> polygon = repo.getExtentByNativeQuery();
  }

}

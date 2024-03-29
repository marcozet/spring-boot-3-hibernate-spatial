package springboot3;

import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;

class PostGISFunctionTests extends AbstractIT {

  private final String polygonWkt = "POLYGON ((10.946712775443787 50.98186912849914, 10.945806152218937 50.97736275655561, "
      + "10.95081643846154 50.97691209530589, 10.951055023520711 50.981628799703174, 10.946712775443787 50.98186912849914))";

  private static final WKTReader wktReader = new WKTReader();

  @Autowired
  private GeometryEntityRepository repo;

  @BeforeEach
  public void setUp() {
    GeometryEntity entity = new GeometryEntity();
    entity.setGeometry(buildPolygon(polygonWkt));
    repo.saveAndFlush(entity);
  }

  @AfterEach
  public void tearDown() {
    repo.deleteAll();
  }

  @Nested
  class GetExtent {

    @Test
    void testGetExtentByJPQL() {
      Optional<Polygon> polygon = repo.getExtentByJPQL();
      Assert.assertTrue(polygon.isPresent());
    }

    @Test
    void testGetExtentByNativeQuery() {
      Optional<Polygon> polygon = repo.getExtentByNativeQuery();
      Assert.assertTrue(polygon.isPresent());
    }

    @Test
    void testGetExtentByST_PrefixedFunctionName() {
      Optional<Polygon> polygon = repo.getExtentByST_PrefixedFunctionName();
      Assert.assertTrue(polygon.isPresent());
    }
  }

  @Nested
  class GetEnvelope {

    @Test
    void testGetEnvelopeByJPQL() {
      Optional<Polygon> polygon = repo.getEnvelopeByJPQL();
      Assert.assertTrue(polygon.isPresent());
    }

    @Test
    void testGetEnvelopeByNativeQuery() {
      Optional<Polygon> polygon = repo.getEnvelopeByNativeQuery();
      Assert.assertTrue(polygon.isPresent());
    }

    @Test
    void testGetEnvelopeByST_PrefixedFunctionName() {
      Optional<Polygon> polygon = repo.getEnvelopeByST_PrefixedFunctionName();
      Assert.assertTrue(polygon.isPresent());
    }
  }

  private static Polygon buildPolygon(String wkt) {
    try {
      Geometry geometry = wktReader.read(wkt);
      geometry.setSRID(4326);
      return (Polygon) geometry;
    } catch (ParseException e) {
      throw new IllegalArgumentException();
    }
  }

}

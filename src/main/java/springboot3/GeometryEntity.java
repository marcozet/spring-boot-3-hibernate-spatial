package springboot3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "field")
@Data
public class GeometryEntity {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(name = "the_geom")
  private Polygon geometry;
}

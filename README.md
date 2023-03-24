# Problem
Testing spatial function extent with Spring Boot 3.0.4 and hibernate-spatial 6.1.7...

[SpringBoot3ApplicationTests](src/test/java/springboot3/SpringBoot3ApplicationTests.java)

Trying to use the PostGIS function st_extent with given JPA repository
```java
public interface GeometryEntityRepository extends CrudRepository<GeometryEntity, UUID> {

  @Query(value = "select extent(geometry) from GeometryEntity")
  Optional<Polygon> getExtent();

  @Query(value = "select st_extent(the_geom) from field", nativeQuery = true)
  Optional<Polygon> getExtentByNativeQuery();

  @Query(value = "select envelope(geometry) from GeometryEntity")
  Optional<Polygon> getEnvelope();
}
```
by using the HQL based query results in an error:

```shell
2023-03-24T17:19:37.115+01:00 ERROR 456308 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : ERROR: function extent(geometry) does not exist
  Hinweis: No function matches the given name and argument types. You might need to add explicit type casts.
  Position: 8

org.springframework.dao.InvalidDataAccessResourceUsageException: JDBC exception executing SQL [select extent(g1_0.the_geom) from field g1_0]; SQL [n/a]
```

The native query works fine.
The query using st_envelope function also works.




package cat.mnp.om.repository;

import cat.mnp.om.domain.MnpPiOrder;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MnpPiOrder entity.
 */
public interface MnpPiOrderRepository extends JpaRepository<MnpPiOrder,Long> {

}

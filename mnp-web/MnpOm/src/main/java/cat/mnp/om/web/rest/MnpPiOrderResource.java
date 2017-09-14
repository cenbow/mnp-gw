package cat.mnp.om.web.rest;

import com.codahale.metrics.annotation.Timed;
import cat.mnp.om.domain.MnpPiOrder;
import cat.mnp.om.repository.MnpPiOrderRepository;
import cat.mnp.om.web.rest.util.HeaderUtil;
import cat.mnp.om.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MnpPiOrder.
 */
@RestController
@RequestMapping("/api")
public class MnpPiOrderResource {

    private final Logger log = LoggerFactory.getLogger(MnpPiOrderResource.class);

    @Inject
    private MnpPiOrderRepository mnpPiOrderRepository;

    /**
     * POST  /mnpPiOrders -> Create a new mnpPiOrder.
     */
    @RequestMapping(value = "/mnpPiOrders",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MnpPiOrder> createMnpPiOrder(@Valid @RequestBody MnpPiOrder mnpPiOrder) throws URISyntaxException {
        log.debug("REST request to save MnpPiOrder : {}", mnpPiOrder);
        if (mnpPiOrder.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new mnpPiOrder cannot already have an ID").body(null);
        }
        MnpPiOrder result = mnpPiOrderRepository.save(mnpPiOrder);
        return ResponseEntity.created(new URI("/api/mnpPiOrders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mnpPiOrder", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mnpPiOrders -> Updates an existing mnpPiOrder.
     */
    @RequestMapping(value = "/mnpPiOrders",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MnpPiOrder> updateMnpPiOrder(@Valid @RequestBody MnpPiOrder mnpPiOrder) throws URISyntaxException {
        log.debug("REST request to update MnpPiOrder : {}", mnpPiOrder);
        if (mnpPiOrder.getId() == null) {
            return createMnpPiOrder(mnpPiOrder);
        }
        MnpPiOrder result = mnpPiOrderRepository.save(mnpPiOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mnpPiOrder", mnpPiOrder.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mnpPiOrders -> get all the mnpPiOrders.
     */
    @RequestMapping(value = "/mnpPiOrders",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MnpPiOrder>> getAllMnpPiOrders(Pageable pageable)
        throws URISyntaxException {
        Page<MnpPiOrder> page = mnpPiOrderRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mnpPiOrders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mnpPiOrders/:id -> get the "id" mnpPiOrder.
     */
    @RequestMapping(value = "/mnpPiOrders/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MnpPiOrder> getMnpPiOrder(@PathVariable Long id) {
        log.debug("REST request to get MnpPiOrder : {}", id);
        return Optional.ofNullable(mnpPiOrderRepository.findOne(id))
            .map(mnpPiOrder -> new ResponseEntity<>(
                mnpPiOrder,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /mnpPiOrders/:id -> delete the "id" mnpPiOrder.
     */
    @RequestMapping(value = "/mnpPiOrders/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMnpPiOrder(@PathVariable Long id) {
        log.debug("REST request to delete MnpPiOrder : {}", id);
        mnpPiOrderRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mnpPiOrder", id.toString())).build();
    }
}

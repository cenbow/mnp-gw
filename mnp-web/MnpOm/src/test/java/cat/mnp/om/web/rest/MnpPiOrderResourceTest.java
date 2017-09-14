package cat.mnp.om.web.rest;

import cat.mnp.om.web.rest.MnpPiOrderResource;
import cat.mnp.om.Application;
import cat.mnp.om.domain.MnpPiOrder;
import cat.mnp.om.repository.MnpPiOrderRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import cat.mnp.om.domain.enumeration.OrderType;
import cat.mnp.om.domain.enumeration.OrderState;
import cat.mnp.om.domain.enumeration.OrderStatus;

/**
 * Test class for the MnpPiOrderResource REST controller.
 *
 * @see MnpPiOrderResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MnpPiOrderResourceTest {

    private static final String DEFAULT_ORDER_ID = "AAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBB";


private static final OrderType DEFAULT_ORDER_TYPE = OrderType.INTERNAL_PORT;
    private static final OrderType UPDATED_ORDER_TYPE = OrderType.INTERNAL_PORT;


private static final OrderState DEFAULT_ORDER_STATE = OrderState.CREATED;
    private static final OrderState UPDATED_ORDER_STATE = OrderState.SUBMITTED;


private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.PROCESSING;
    private static final OrderStatus UPDATED_ORDER_STATUS = OrderStatus.SUCCESS;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SUBMITTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBMITTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_REMARK = "AAAAA";
    private static final String UPDATED_REMARK = "BBBBB";

    @Inject
    private MnpPiOrderRepository mnpPiOrderRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMnpPiOrderMockMvc;

    private MnpPiOrder mnpPiOrder;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MnpPiOrderResource mnpPiOrderResource = new MnpPiOrderResource();
        ReflectionTestUtils.setField(mnpPiOrderResource, "mnpPiOrderRepository", mnpPiOrderRepository);
        this.restMnpPiOrderMockMvc = MockMvcBuilders.standaloneSetup(mnpPiOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        mnpPiOrder = new MnpPiOrder();
        mnpPiOrder.setOrderId(DEFAULT_ORDER_ID);
        mnpPiOrder.setOrderType(DEFAULT_ORDER_TYPE);
        mnpPiOrder.setOrderState(DEFAULT_ORDER_STATE);
        mnpPiOrder.setOrderStatus(DEFAULT_ORDER_STATUS);
        mnpPiOrder.setCreatedDate(DEFAULT_CREATED_DATE);
        mnpPiOrder.setSubmittedDate(DEFAULT_SUBMITTED_DATE);
        mnpPiOrder.setUpdatedDate(DEFAULT_UPDATED_DATE);
        mnpPiOrder.setRemark(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createMnpPiOrder() throws Exception {
        int databaseSizeBeforeCreate = mnpPiOrderRepository.findAll().size();

        // Create the MnpPiOrder

        restMnpPiOrderMockMvc.perform(post("/api/mnpPiOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mnpPiOrder)))
                .andExpect(status().isCreated());

        // Validate the MnpPiOrder in the database
        List<MnpPiOrder> mnpPiOrders = mnpPiOrderRepository.findAll();
        assertThat(mnpPiOrders).hasSize(databaseSizeBeforeCreate + 1);
        MnpPiOrder testMnpPiOrder = mnpPiOrders.get(mnpPiOrders.size() - 1);
        assertThat(testMnpPiOrder.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testMnpPiOrder.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testMnpPiOrder.getOrderState()).isEqualTo(DEFAULT_ORDER_STATE);
        assertThat(testMnpPiOrder.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testMnpPiOrder.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMnpPiOrder.getSubmittedDate()).isEqualTo(DEFAULT_SUBMITTED_DATE);
        assertThat(testMnpPiOrder.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testMnpPiOrder.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void checkOrderTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mnpPiOrderRepository.findAll().size();
        // set the field null
        mnpPiOrder.setOrderType(null);

        // Create the MnpPiOrder, which fails.

        restMnpPiOrderMockMvc.perform(post("/api/mnpPiOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mnpPiOrder)))
                .andExpect(status().isBadRequest());

        List<MnpPiOrder> mnpPiOrders = mnpPiOrderRepository.findAll();
        assertThat(mnpPiOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMnpPiOrders() throws Exception {
        // Initialize the database
        mnpPiOrderRepository.saveAndFlush(mnpPiOrder);

        // Get all the mnpPiOrders
        restMnpPiOrderMockMvc.perform(get("/api/mnpPiOrders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(mnpPiOrder.getId().intValue())))
                .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
                .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE.toString())))
                .andExpect(jsonPath("$.[*].orderState").value(hasItem(DEFAULT_ORDER_STATE.toString())))
                .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
                .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
                .andExpect(jsonPath("$.[*].submittedDate").value(hasItem(DEFAULT_SUBMITTED_DATE.toString())))
                .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
                .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getMnpPiOrder() throws Exception {
        // Initialize the database
        mnpPiOrderRepository.saveAndFlush(mnpPiOrder);

        // Get the mnpPiOrder
        restMnpPiOrderMockMvc.perform(get("/api/mnpPiOrders/{id}", mnpPiOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(mnpPiOrder.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE.toString()))
            .andExpect(jsonPath("$.orderState").value(DEFAULT_ORDER_STATE.toString()))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.submittedDate").value(DEFAULT_SUBMITTED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMnpPiOrder() throws Exception {
        // Get the mnpPiOrder
        restMnpPiOrderMockMvc.perform(get("/api/mnpPiOrders/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMnpPiOrder() throws Exception {
        // Initialize the database
        mnpPiOrderRepository.saveAndFlush(mnpPiOrder);

		int databaseSizeBeforeUpdate = mnpPiOrderRepository.findAll().size();

        // Update the mnpPiOrder
        mnpPiOrder.setOrderId(UPDATED_ORDER_ID);
        mnpPiOrder.setOrderType(UPDATED_ORDER_TYPE);
        mnpPiOrder.setOrderState(UPDATED_ORDER_STATE);
        mnpPiOrder.setOrderStatus(UPDATED_ORDER_STATUS);
        mnpPiOrder.setCreatedDate(UPDATED_CREATED_DATE);
        mnpPiOrder.setSubmittedDate(UPDATED_SUBMITTED_DATE);
        mnpPiOrder.setUpdatedDate(UPDATED_UPDATED_DATE);
        mnpPiOrder.setRemark(UPDATED_REMARK);

        restMnpPiOrderMockMvc.perform(put("/api/mnpPiOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mnpPiOrder)))
                .andExpect(status().isOk());

        // Validate the MnpPiOrder in the database
        List<MnpPiOrder> mnpPiOrders = mnpPiOrderRepository.findAll();
        assertThat(mnpPiOrders).hasSize(databaseSizeBeforeUpdate);
        MnpPiOrder testMnpPiOrder = mnpPiOrders.get(mnpPiOrders.size() - 1);
        assertThat(testMnpPiOrder.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testMnpPiOrder.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testMnpPiOrder.getOrderState()).isEqualTo(UPDATED_ORDER_STATE);
        assertThat(testMnpPiOrder.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testMnpPiOrder.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMnpPiOrder.getSubmittedDate()).isEqualTo(UPDATED_SUBMITTED_DATE);
        assertThat(testMnpPiOrder.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testMnpPiOrder.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void deleteMnpPiOrder() throws Exception {
        // Initialize the database
        mnpPiOrderRepository.saveAndFlush(mnpPiOrder);

		int databaseSizeBeforeDelete = mnpPiOrderRepository.findAll().size();

        // Get the mnpPiOrder
        restMnpPiOrderMockMvc.perform(delete("/api/mnpPiOrders/{id}", mnpPiOrder.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MnpPiOrder> mnpPiOrders = mnpPiOrderRepository.findAll();
        assertThat(mnpPiOrders).hasSize(databaseSizeBeforeDelete - 1);
    }
}

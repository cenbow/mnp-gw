/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.splitter;

import cat.mnp.pincode.core.aggregator.GeneratePinCodeRequestAggregator;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.integration.annotation.Splitter;

/**
 *
 * @author CATr
 */
public class GeneratePinCodeRequestBatchSizeSplitter {
    
    private int size = 1;

    public void setSize(int size) {
        this.size = size;
    }
    
    @Splitter
    public List<GeneratePinCodeRequest> split(GeneratePinCodeRequest req) {
        GeneratePinCodeRequestSplitter splitter = new GeneratePinCodeRequestSplitter();
        List<GeneratePinCodeRequest> list = splitter.split(req);
        
        List<GeneratePinCodeRequest> pagedList = new ArrayList<>();
        GeneratePinCodeRequestAggregator aggregator = new GeneratePinCodeRequestAggregator();
        
        PagedListHolder<GeneratePinCodeRequest> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(size);
        
        for (int i = 0; i < pagedListHolder.getPageCount(); i++) {
            pagedListHolder.setPage(i);
            GeneratePinCodeRequest pagedResult = aggregator.aggregate(pagedListHolder.getPageList());
            pagedList.add(pagedResult);
        }
        
        return pagedList;
    }
}

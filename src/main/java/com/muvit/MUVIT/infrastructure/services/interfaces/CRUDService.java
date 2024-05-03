package com.muvit.MUVIT.infrastructure.services.interfaces;

import org.springframework.data.domain.Page;

public interface CRUDService<RQ, RS, ID>{
        public RS create(RQ request);
        public RS update(ID id, RQ request);
        public void delete(ID id);
        public Page<RS> getAll(int page, int size);
}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository; // 필드 주입

    // 재고 저장
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 재고 리스트 조회
    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    // 재고 단건 조회
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}

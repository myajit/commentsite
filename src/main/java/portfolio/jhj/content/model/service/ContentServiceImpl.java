package portfolio.jhj.content.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.jhj.content.model.Repository.ContentRepository;
import portfolio.jhj.content.model.dto.Content;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{

    private final ContentRepository contentRepository;

    public void insert(Content content) {
         contentRepository.insert(content);
    }

    public List<Content> selectAll() {
        return contentRepository.selectAll();
    }
}

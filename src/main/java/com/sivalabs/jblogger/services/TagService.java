package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.entities.Tag;
import com.sivalabs.jblogger.exceptions.JBloggerException;
import com.sivalabs.jblogger.repositories.TagRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> search(String query) {
        return tagRepository.findByLabelLike(query + "%");
    }

    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Cacheable(value = "tags.item")
    public Optional<Tag> findByLabel(String label) {
        return tagRepository.findByLabel(label.trim());
    }

    @CacheEvict(
            value = {"tags.counts", "tags.all"},
            allEntries = true)
    public Tag createTag(Tag tag) {
        if (findByLabel(tag.getLabel()).isPresent()) {
            throw new JBloggerException("Tag [" + tag.getLabel() + "] already exists");
        }
        return tagRepository.save(tag);
    }

    @Cacheable(value = "tags.all")
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Cacheable(value = "tags.counts")
    public Map<Tag, Integer> getTagsWithCount() {
        Map<Tag, Integer> map = new TreeMap<>();

        List<Object[]> tagsWithCount = tagRepository.getTagsWithCount();
        for (Object[] objects : tagsWithCount) {
            Tag tag = new Tag();
            tag.setId(Long.parseLong(String.valueOf(objects[0])));
            tag.setLabel(String.valueOf(objects[1]));
            Integer count = Integer.parseInt(String.valueOf(objects[2]));
            map.put(tag, count);
        }

        return map;
    }
}

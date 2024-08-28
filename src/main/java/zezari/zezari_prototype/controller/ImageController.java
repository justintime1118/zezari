package zezari.zezari_prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import zezari.zezari_prototype.service.PageService;

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final PageService pageService;

    @ResponseBody
    @GetMapping("images/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws MalformedURLException {
        return new UrlResource("file:" + pageService.getFullPath(filename));
    }
}

package org.optigra.ads.rest.web.controller;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.optigra.ads.content.model.ContentStrategy;
import org.optigra.ads.facade.content.ContentFacade;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.content.ContentResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContentController {

    //@Resource(name = "contentFacade")
    private ContentFacade contentFacade;
    
    @RequestMapping(value = ResourceUri.CONTENT, method = RequestMethod.GET)
    public void getContentByPath(final HttpServletResponse response, @RequestParam("contentPath") final String imagePath) throws Exception {
        InputStream in = contentFacade.getContentByPath(imagePath);
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
    }
    
    @RequestMapping(value = ResourceUri.CONTENT, method = RequestMethod.POST)
    public @ResponseBody ContentResource saveContent(@RequestPart("file") final MultipartFile file) throws Exception {
        return processContent(file, ContentStrategy.DEFAULT);
    }

    private ContentResource processContent(final MultipartFile file, final ContentStrategy contentStrategy) throws Exception {
        
        InputStream stream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        
        return contentFacade.storeContent(stream, fileName,  contentStrategy);
    }
    
}

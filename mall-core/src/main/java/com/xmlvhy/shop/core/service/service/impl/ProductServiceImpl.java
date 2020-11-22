package com.xmlvhy.shop.core.service.service.impl;

import com.xmlvhy.shop.core.common.utils.StringUtil;
import com.xmlvhy.shop.core.dal.mapper.ProductDao;
import com.xmlvhy.shop.core.dal.params.ProductParam;
import com.xmlvhy.shop.core.pojo.Product;
import com.xmlvhy.shop.core.pojo.ProductType;
import com.xmlvhy.shop.core.service.dto.ProductDto;
import com.xmlvhy.shop.core.service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: 小莫
 * Date: 2019-03-10 10:18
 * Description:  商品业务层接口实现
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Value("${biz.ftp.host}")
    private String host;

    @Value("${biz.ftp.port}")
    private Integer port;

    @Value("${biz.ftp.username}")
    private String username;

    @Value("${biz.ftp.password}")
    private String password;

    @Value("${biz.ftp.basePath}")
    private String basePath;

    @Value("${biz.ftp.path}")
    private String path;

    @Value("${biz.ftp.baseUrl}")
    private String baseUrl;

    /**
     * 功能描述: 添加商品
     *
     * @return int
     * @Author 小莫
     * @Date 20:24 2019/03/14
     * @Param [productDto]
     */
    @Override
    public int addProduct(ProductDto productDto) {
        //1.文件上传
        //String fileName = StringUtil.reFileName(productDto.getFileName());
        //String filePath = productDto.getUploadPath()+"\\"+ fileName;
        //
        //try {
        //    StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        //} catch (IOException e) {
        //    throw new FileUploadException("文件上传失败"+e.getMessage());
        //}

        //将文件上传ftp服务器上
        //按照时间创建文件夹
        String timePath = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String filePath = path + "/" + timePath;
        String fileName = StringUtil.reFileName(productDto.getFileName());
        //TODO 保存商品图片
        //boolean flag = FileUtils.putFile(host, port, username, password, basePath, filePath, fileName, productDto.getInputStream());
        //
        //if (!flag) {
        //    throw new IllegalStateException("文件上传失败");
        //}

        //2.保存到数据库,将 dto 转化为 pojo
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        //product.setImage(filePath);
        //地址修改为服务器上的http地址
        //product.setImage(baseUrl+"/" + filePath + "/" + fileName);
        product.setImage(baseUrl + "/" + timePath + "/" + fileName);


        ProductType productType = new ProductType();
        productType.setId(productDto.getProductTypeId());

        product.setProductType(productType);

        return productDao.insertProduct(product);
    }

    /**
     * 功能描述: 检测商品名称是否可用
     *
     * @return java.lang.Boolean true表示可用， false表示不可用
     * @Author 小莫
     * @Date 14:39 2019/03/10
     * @Param [name]
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Boolean checkProductName(String name) {
        Product product = productDao.selectByProductName(name);
        if (product != null) {
            //商品名称已经存在
            return false;
        }
        return true;
    }

    /**
     * 功能描述: 查找所有商品
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.Product>
     * @Author 小莫
     * @Date 20:25 2019/03/14
     * @Param []
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        return productDao.selectAllProducts();
    }

    /**
     * 功能描述: 根据 id 查找商品
     *
     * @return com.xmlvhy.shop.pojo.Product
     * @Author 小莫
     * @Date 20:25 2019/03/14
     * @Param [id]
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Product findProductById(int id) {
        return productDao.selectProductById(id);
    }

    /**
     * 功能描述: 修改商品
     *
     * @return int
     * @Author 小莫
     * @Date 20:25 2019/03/14
     * @Param [productDto]
     */
    @Override
    public int modifyProduct(ProductDto productDto) {
        //1.文件上传
        //String fileName = StringUtil.reFileName(productDto.getFileName());
        //String filePath = productDto.getUploadPath()+"\\"+ fileName;
        //
        //try {
        //    StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        //} catch (IOException e) {
        //    throw new FileUploadException("文件上传失败"+e.getMessage());
        //}

        String timePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String filePath = path + "/" + timePath;
        String fileName = StringUtil.reFileName(productDto.getFileName());

        //TODO 保存商品图片
        //boolean flag = FileUtils.putFile(host, port, username, password, basePath, filePath, fileName, productDto.getInputStream());
        //if (!flag) {
        //    throw new IllegalStateException("文件上传失败");
        //}

        //2.保存到数据库,将 dto 转化为 pojo
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        //product.setImage(filePath);
        //https://www.xmlvhy.com/images/20190301512474268.jpg
        product.setImage(baseUrl + "/" + timePath + "/" + fileName);

        ProductType productType = new ProductType();
        productType.setId(productDto.getProductTypeId());

        product.setProductType(productType);
        return productDao.updateProduct(product);
    }

    /**
     * 功能描述: 删除商品
     *
     * @return int
     * @Author 小莫
     * @Date 20:25 2019/03/14
     * @Param [id]
     */
    @Override
    public int removeProductById(int id) {
        return productDao.deleteProductById(id);
    }

    /**
     * 功能描述: 获取图片，将图片响应到输出流中，显示图片
     *
     * @return void
     * @Author 小莫
     * @Date 21:27 2019/03/10
     * @Param [path, outputStream]
     */
    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 多条件查找商品
     *
     * @return java.util.List<com.xmlvhy.shop.pojo.Product>
     * @Author 小莫
     * @Date 20:26 2019/03/14
     * @Param [productParam]
     */
    @Override
    public List<Product> findByProductParams(ProductParam productParam) {
        return productDao.selectByProductParams(productParam);
    }
}

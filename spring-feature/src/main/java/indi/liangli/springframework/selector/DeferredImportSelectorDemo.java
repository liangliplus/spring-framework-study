package indi.liangli.springframework.selector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @Import 注解主要是导入一些第三方依赖（希望把第三方的类变为BeanDefinition）
 * 学习spring的 DeferredImportSelector
 * 该类 实现了 ImportSelector 接口，并在内部定义了一个Group。 这个Group是做什么的 ？
 *
 *
 */
public class DeferredImportSelectorDemo implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //返回类的限定名
        return new String[] {ExternalBean.class.getName()};
    }


    @Override
    public Class<? extends Group> getImportGroup() {
        return null;
    }


    private static class DeferredImportSelectorGroupDemo implements Group {

        private List<Entry> list = new ArrayList<>();


        /**
         * 在process中处理需要收集的类。
         * 调用selector的selectImports 方法
         * 我们这里通过一个集合保存下来,注意元素是Entry类型（包含类类名 + metadata）
         * 最后在group的 selectImports 返回收集的entry集合
         * @param metadata
         * @param selector
         */
        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            String[] strings = selector.selectImports(metadata);
            for (String string : strings) {
                list.add(new Entry(metadata,string));
            }

        }

        @Override
        public Iterable<Entry> selectImports() {
            return list;
        }
    }
}

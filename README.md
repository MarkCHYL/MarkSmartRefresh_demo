# SmartRefresh_demo
SmartRefresh的demo   参考地址：http://mp.weixin.qq.com/s/_Kpt7Qk1NZbCGVz9b3I4wQ
修改状态栏的背景颜色和字体颜色

    //SmartRefresh需要：
    compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.2-alpha-8'
    compile 'com.scwang.smartrefresh:SmartRefreshHeader:1.0.2-alpha-8'//如果使用了特殊的Header
    //加载图片需要的Glide
    compile 'com.github.bumptech.glide:glide:3.7.0'
    //banner的架包
    compile 'com.youth.banner:banner:1.4.9'//最新版本
    //状态栏的背景和字体颜色的修改架包
    compile 'com.githang:status-bar-compat:0.5.2'
    
图片加载器由自己选择，这里不限制，只是提供几种使用方法

public class GlideImageLoader  extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

        //Picasso 加载图片简单用法
//        Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }


   /* //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;
    }*/


}

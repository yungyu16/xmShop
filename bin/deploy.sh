#! /bin/bash
#检查参数
if [ $# -lt 1 ]; then
  echo 请指定需要打包的系统 b=后台系统 f=商城系统
  exit 1
fi
#检查是否安装docker
if type docker >/dev/null 2>&1; then
  echo 'docker exists'
else
  echo 'docker not exists,start install... '
  curl -fsSL https://get.docker.com | sh
fi

docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2  -w /usr/src/mymaven maven:3-adoptopenjdk-8  mvn -Dmaven.test.skip=true clean package

IMG_NAME="xmshop-front"
TIMESTAMP=$(date "+%Y%m%d%H%M%S")
PORT_MAPPING="80:8080"
case $1 in
f)
  cp xm_shop_front_web/target/xmShopFront.war docker/ROOT.war
  ;;
b)
  cp xm_shop_back_web/target/xmShopBack.war docker/ROOT.war
  IMG_NAME="xmshop-back"
  PORT_MAPPING="8081:8081"
  ;;
*)
  echo '参数不合法! 请指定需要打包的系统 b=后台系统 f=商城系统'
  exit 1
  ;;
esac
IMG_FULL_NAME="$IMG_NAME":"$TIMESTAMP"
echo 开始build镜像:$IMG_FULL_NAME
docker build -t $IMG_FULL_NAME docker
docker tag $IMG_FULL_NAME "$IMG_NAME":latest
docker stop $IMG_NAME || echo 关闭$IMG_NAME失败,容器不存在或无法关闭,请留意
docker run -d --rm --name=$IMG_NAME -p $PORT_MAPPING $IMG_FULL_NAME

if [ $# -lt 1 ]; then
  echo 请指定需要打包的系统 b=后台系统 f=商城系统
  exit 1
fi

docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2  -w /usr/src/mymaven maven:3-adoptopenjdk-8  mvn -Dmaven.test.skip=true clean package

IMG_NAME="xmshop-front"
TIMESTAMP=$(date "+%Y%m%d%H%M%S")
case $1 in
f)
  cp xm_shop_front_web/target/xmShopFront.war docker/ROOT.war
  ;;
b)
  cp xm_shop_back_web/target/xmShopBack.war docker/ROOT.war
  IMG_NAME="xmshop-back"
  ;;
*)
  echo '参数不合法! 请指定需要打包的系统 b=后台系统 f=商城系统'
  exit 1
  ;;
esac
cd docker || exit 1
docker build -t "$IMG_NAME":"$TIMESTAMP"
docker tag "$IMG_NAME":"$TIMESTAMP" "$IMG_NAME":latest

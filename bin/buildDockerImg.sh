docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2  -w /usr/src/mymaven maven:3-adoptopenjdk-8  mvn clean package
if [ $# -gt 1 ]; then
  echo 请指定需要打包的系统 b=后台系统 f=商城系统
  exit 1
fi

imgName="front"
case $1 in
f)
  cp xm_shop_front_web/target/xmShopFront.war docker/ROOT.war
  ;;
b)
  cp xm_shop_back_web/target/xmShopBack.war docker/ROOT.war
  ;;
*)
  echo '参数不合法! 请指定需要打包的系统 b=后台系统 f=商城系统'
  exit 1
  ;;
esac
cd docker
docker build

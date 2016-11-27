require 'require_all'
require_rel '../place'
require_relative 'config.rb'

class Map
  def initialize
    @places = []

    @places << Starting.new
    @places << district_lands(MapConf::FIRST_DISTRICT_LANDS_NUM,
                              MapConf::FIRST_DISTRICT_LANDS_PRICE)
    @places << Hospital.new
    @places << district_lands(MapConf::SECOND_DISTRICT_LANDS_NUM,
                              MapConf::SECOND_DISTRICT_LANDS_PRICE)
    @places << ToolRoom.new
    @places << district_lands(MapConf::THIRD_DISTRICT_LANDS_NUM,
                              MapConf::THIRD_DISTRICT_LANDS_PRICE)
    @places << GiftRoom.new
    @places << district_lands(MapConf::FOURTH_DISTRICT_LANDS_NUM,
                              MapConf::FOURTH_DISTRICT_LANDS_PRICE)
    @places << Prison.new
    @places << district_lands(MapConf::FIFTH_DISTRICT_LANDS_NUM,
                              MapConf::FIFTH_DISTRICT_LANDS_PRICE)
    @places << MagicRoom.new
    @places << mineral_estates
    @places.flatten!
  end

  def to_s

  end

  private
  def district_lands(num, price)
    ret = []
    num.times do
      ret << Land.new(price)
    end
    ret
  end

  def mineral_estates
    ret = []
    [20, 80, 100, 40, 80, 60].each do |points|
      ret << MineralEstate.new(points)
    end
  end


end

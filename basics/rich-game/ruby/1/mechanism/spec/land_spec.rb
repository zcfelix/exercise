require_relative '../lib/game/player.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'

describe Land do

  before(:each) do
    @land_price = 200
    @top_level = 3
    @land = Land.new(@land_price)
  end

  it 'should player buy empty land' do
    player = Player.new :balance => @land_price
    expect(player.buy_land(@land)).to eq(true)
    expect(player.balance).to eq(0)
    expect(@land.owner).to eq(player)
    expect(player.lands.size).to eq(1)
  end

  it 'should player not buy empty land when balance is not enough' do
    player = Player.new :balance => 0
    expect(player.buy_land(@land)).to eq(false)
    expect(player.lands.size).to eq(0)
    expect(@land.owner).to eq(nil)
  end

  it 'should player not buy others land' do
    player = Player.new :balance => @land_price
    @land.owner = Player.new
    expect(player.buy_land(@land)).to eq(false)
    expect(player.balance).to eq(@land_price)
    expect(player.lands.size).to eq(0)
  end

  it 'should player upgrade land' do
    player = Player.new :balance => (@land_price * 2)
    expect(player.buy_land(@land)).to eq(true)
    expect(player.upgrade(@land)).to eq(true)
    expect(player.balance).to eq(0)
    expect(@land.level).to eq(1)
  end

  it 'should player not upgrade land when balance is not enough' do
    player = Player.new :balance => (@land_price * 2 - 1)
    expect(player.buy_land(@land)).to eq(true)
    expect(player.upgrade(@land)).to eq(false)
    expect(player.balance).to eq(@land_price - 1)
    expect(@land.level).to eq(0)
  end

  it 'should player not upgrade land when land is top level' do
    player = Player.new :balance => (@land_price * (@top_level + 1))
    # buy land and upgrade to top level
    expect(player.buy_land(@land)).to eq(true)
    until @land.level == @top_level do
        expect(player.upgrade(@land)).to eq(true)
      end
      # can not upgrade anymore when land is top level
      expect(player.upgrade(@land)).to eq(false)
      expect(player.balance).to eq(0)
      expect(@land.level).to eq(@top_level)
    end

    it 'should player not upgrade others land' do
      player = Player.new :balance => @land_price
      @land.owner = Player.new
      expect(player.upgrade(@land)).to eq(false)
      expect(player.balance).to eq(@land_price)
      expect(player.lands.size).to eq(0)
      expect(@land.level).to eq(0)
    end


  end

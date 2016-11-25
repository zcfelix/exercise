require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'
require_relative '../lib/place/tool_room.rb'
require_relative '../lib/place/gift_room.rb'
require_relative '../lib/game/config.rb'

describe Response do

  before(:each) do
    @target = Land.new(200)
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
    allow(@map).to receive(:move) {@target}
  end

  it 'should not buy land when player response no' do
    player = Player.new :balance => 200
    player.execute(@roll)
    player.respond(:no)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(200)
    expect(@target.owner).to eq(nil)
  end

  it 'should buy land when player response yes and balance is enough' do
    player = Player.new :balance => 200
    player.execute(@roll)
    player.respond(:yes)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(0)
    expect(@target.owner).to eq(player)
    expect(player.lands.size).to eq(1)
    expect(@target.road_toll).to eq(@target.price / 2)
  end

  it 'should not buy land when player response yes but balance is not enough' do
    player = Player.new :balance => 100
    player.execute(@roll)
    player.respond(:yes)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(100)
    expect(@target.owner).to eq(nil)
    expect(player.lands.size).to eq(0)
    expect(@target.road_toll).to eq(0)
  end

  it 'should not upgrade land when player response no' do
    player = Player.new :balance => 400
    player.buy_land(@target)

    player.execute(@roll)
    player.respond(:no)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(200)
    expect(@target.owner).to eq(player)
    expect(player.lands.size).to eq(1)
    expect(@target.road_toll).to eq(@target.price / 2)
    expect(@target.level).to eq(0)
  end

  it 'should upgrade land when player response yes' do
    player = Player.new :balance => 400
    player.buy_land(@target)

    player.execute(@roll)
    player.respond(:yes)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(0)
    expect(@target.owner).to eq(player)
    expect(player.lands.size).to eq(1)
    expect(@target.level).to eq(1)
    expect(@target.road_toll).to eq(@target.price)
  end

  it 'should not upgrade land when player response yes but balance is not enough' do
    player = Player.new :balance => 300
    player.buy_land(@target)

    player.execute(@roll)
    player.respond(:yes)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(100)
    expect(@target.owner).to eq(player)
    expect(player.lands.size).to eq(1)
    expect(@target.level).to eq(0)
    expect(@target.road_toll).to eq(@target.price / 2)
  end

  it 'should not upgrade land when player response yes but land is top level' do
    player = Player.new :balance => 1000
    player.buy_land(@target)

    LandConf::TOP_LEVEL.times do
      player.upgrade(@target)
    end

    player.execute(@roll)
    player.respond(:yes)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(200)
    expect(@target.owner).to eq(player)
    expect(player.lands.size).to eq(1)
    expect(@target.level).to eq(LandConf::TOP_LEVEL)
    expect(@target.road_toll).to eq(@target.price * 2 ** (LandConf::TOP_LEVEL - 1))
  end

  it 'should end turn when player come to others land and balance is enough' do
    player = Player.new :balance => 1000
    other_player = Player.new :balance => 0
    @target.owner = other_player

    player.execute(@roll)
    expect(player.status).to eq(:end_turn)
    expect(player.balance).to eq(1000 - @target.road_toll)
    expect(other_player.balance).to eq(@target.road_toll)
  end

  it 'should player game over when come to others land and balance is not enough' do
    player = Player.new :balance => 0
    other_player = Player.new :balance => 0
    @target.owner = other_player

    player.execute(@roll)
    expect(player.status).to eq(:game_over)
    expect(player.balance).to eq(0 - @target.road_toll)
    expect(other_player.balance).to eq(@target.road_toll)
  end

end

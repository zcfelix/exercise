require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'
require_relative '../lib/place/tool_room.rb'
require_relative '../lib/place/gift_room.rb'

describe RollCommand do

  before(:each) do
    @player = Player.new
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
  end

  it 'should change player status to wait_for_buy on empty land' do
    target = Land.new(200)
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:wait_for_buy)
  end

  it 'should change player status to wait_for_upgrade on self land' do
    target = Land.new(200)
    allow(target).to receive(:visit_by) {:wait_for_upgrade}
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:wait_for_upgrade)
  end

  it 'should change playe status to end_turn on others land' do
    target = Land.new(200)
    allow(target).to receive(:visit_by) {:end_turn}
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:end_turn)
  end

  it 'should change player status to wait_for_buy_tool in tool room' do
    target = ToolRoom.new
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:wait_for_buy_tool)
  end

  it 'should change player status to wait_for_choose_gift in gift room' do
    target = GiftRoom.new
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:wait_for_choose_gift)
  end

  it 'should change player status to end_turn in other place' do
    target = Place.new
    allow(@map).to receive(:move) {target}
    @player.execute(@roll)
    expect(@player.status).to eq(:end_turn)
  end


end

require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'
require_relative '../lib/place/tool_room.rb'
require_relative '../lib/place/gift_room.rb'
require_relative '../lib/game/config.rb'
require_relative '../lib/tool/tool.rb'
require_relative '../lib/tool/bomb.rb'
require_relative '../lib/tool/block.rb'
require_relative '../lib/tool/robot.rb'

describe RollCommand do
  before(:each) do
    @step = 1
    @target = ToolRoom.new
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
    allow(@map).to receive(:move) {@target}
  end

  it 'should not buy tool when player response exit' do
    player = Player.new :points => 200
    player.execute(@roll)
    expect(player.status).to eq(:wait_for_buy_tool)
    player.respond(:exit)
    expect(player.status).to eq(:end_turn)
    expect(player.points).to eq(200)
    expect(player.tool_quantity).to eq(0)
  end

  it 'should buy block when player response 1 and points are enough' do
    player = Player.new :points => 200
    player.execute(@roll)
    expect(player.status).to eq(:wait_for_buy_tool)
    player.respond(:buy_block)
    expect(player.status).to eq(:end_turn)
    expect(player.points).to eq(200 - Block.new.points)
    expect(player.tool_quantity).to eq(1)
  end

  it 'should not buy block when player response 1 but the tool quantity is max' do
    player = Player.new :points => 2000

    ToolConf::MAX_QUANTITY.times do
      player.buy_tool(Bomb.new)
    end

    player.execute(@roll)
    expect(player.status).to eq(:wait_for_buy_tool)
    player.respond(:buy_block)
    expect(player.status).to eq(:end_turn)
    expect(player.points).to eq(2000 - Bomb.new.points * ToolConf::MAX_QUANTITY)
    expect(player.tool_quantity).to eq(ToolConf::MAX_QUANTITY)
  end

  it 'should not buy block when player response 1 but points are not enough' do
    player = Player.new :points => 0

    player.execute(@roll)
    expect(player.status).to eq(:wait_for_buy_tool)
    player.respond(:buy_block)
    expect(player.status).to eq(:end_turn)
    expect(player.points).to eq(0)
    expect(player.tool_quantity).to eq(0)
  end

end
